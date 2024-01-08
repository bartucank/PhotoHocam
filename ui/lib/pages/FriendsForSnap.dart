import 'package:flutter/material.dart';
import '../utils/ApiService.dart';



class FriendsForSnap extends StatefulWidget {

  final String imagePath;

  const FriendsForSnap({super.key, required this.imagePath});
  @override
  _FriendsForSnapState createState() => _FriendsForSnapState();
}

class _FriendsForSnapState extends State<FriendsForSnap> {
  final ApiService _apiService = ApiService();
  List<dynamic> _friendList = [];

  @override
  void initState() {
    super.initState();
    print("unfriend start");
    fetchData();
    print("end");
  }

  Future<void> fetchData() async {
    try {
      final friendList = await _apiService.getFriends();
      setState(() {
        _friendList = friendList;
      });
    } catch (e) {
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Send This Snap to Your Friend!'),
      ),
      body: ListView.builder(
        itemCount: _friendList.length,
        itemBuilder: (context, index) {
          final unfriend = _friendList[index];
          return ListTile(
            title: Text(unfriend['name']),
            trailing: ElevatedButton(
              onPressed: () async {
                await _apiService.sendImage(widget.imagePath,unfriend['id']);
                Navigator.pushReplacementNamed(context, '/cam');
              },
              child: Text('Send!'),
            ),
          );
        },
      ),
    );
  }
}
