import 'dart:convert';
import 'dart:typed_data';

import 'package:flutter/material.dart';
import 'package:photohocamui/pages/ViewReceivedSnapPage.dart';
import '../utils/ApiService.dart';

class MessagesPage extends StatefulWidget {
  @override
  _MessagesPageState createState() => _MessagesPageState();
}

class _MessagesPageState extends State<MessagesPage> {
  final ApiService _apiService = ApiService();
  List<dynamic> _messagesList = [];

  @override
  void initState() {
    super.initState();
    fetchMessages();
  }

  Future<void> fetchMessages() async {
    try {
      final messagesList = await _apiService.getImagelist();
      setState(() {
        _messagesList = messagesList;
      });
    } catch (e) {
      //do nothing
    }
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Snaps'),
      ),
      body: ListView.builder(
        itemCount: _messagesList.length,
        itemBuilder: (context, index) {
          final message = _messagesList[index];
          int size = 0;
          String sizemsg = "";
          if(message != null && message['imageList'] != null && message['imageList'].length > 0 ){
            size =  message['imageList'].length;
          }
          if(size > 1){
            sizemsg = "There are $size new snaps!";
          }else{
            sizemsg = "There is $size new snap!";

          }
          return InkWell(
            onTap: () {
              String base64ImageData = message['imageList'][0]['data'];
              List<int> compressedImageData = base64Decode(base64ImageData);

              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => ViewReceivedSnapPage(compressedImageData: compressedImageData, apiService: _apiService, imgid: message['imageList'][0]['id']),
                ),
              );
            },
            child: Column(
              children: [
                ListTile(
                  title: Padding(
                    padding: const EdgeInsets.fromLTRB(15,0,0,0),
                    child: Text(
                      message['username'],
                      style: TextStyle(
                        fontSize: 28,
                      ),
                    ),
                  ),
                  trailing: Column(
                    children: [
                      Text(
                        sizemsg,
                        style: TextStyle(

                          fontSize: 20,
                          color: Colors.red
                        ),
                      ),
                      Text(
                        "Click to open",
                        style: TextStyle(

                          fontSize: 15,
                          color: Colors.red
                        ),
                      ),
                    ],
                  ),
                ),
                Divider(height: 10,),
              ],
            ),
          );
        },
      ),
    );
  }
}
