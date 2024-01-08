import 'package:flutter/material.dart';
import '../utils/ApiService.dart';


class FriendPage extends StatefulWidget {
  @override
  _FriendPageState createState() => _FriendPageState();
}

class _FriendPageState extends State<FriendPage> {
  final ApiService _apiService = ApiService();
  List<dynamic> _unfriendList = [];

  @override
  void initState() {
    super.initState();
    print("unfriend start");
    fetchData();
    print("end");
  }

  Future<void> fetchData() async {
    try {
      final unfriendList = await _apiService.getUnfriends();
      setState(() {
        _unfriendList = unfriendList;
      });
    } catch (e) {
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Add a friend'),
      ),
      body: ListView.builder(
        itemCount: _unfriendList.length,
        itemBuilder: (context, index) {
          final unfriend = _unfriendList[index];
          return ListTile(
            title: Text(unfriend['name']),
            trailing: ElevatedButton(
              onPressed: () async {
                await _apiService.addFriend(unfriend['id']);
                fetchData();
              },
              child: Text('Send Friend Request'),
            ),
          );
        },
      ),
    );
  }
}
