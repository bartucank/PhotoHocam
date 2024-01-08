import 'package:flutter/material.dart';
import '../utils/ApiService.dart';


class FriendRequestPage extends StatefulWidget {
  @override
  _FriendRequestPageState createState() => _FriendRequestPageState();
}

class _FriendRequestPageState extends State<FriendRequestPage> {
  final ApiService _apiService = ApiService();
  List<dynamic> _unfriendList = [];

  @override
  void initState() {
    super.initState();
    fetchData();
  }

  Future<void> fetchData() async {
    try {
      final unfriendList = await _apiService.getFriendRequestList();
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
        title: Text('Approve Friend Requests'),
      ),
      body: ListView.builder(
        itemCount: _unfriendList.length,
        itemBuilder: (context, index) {
          final unfriend = _unfriendList[index];
          return ListTile(
            title: Text(unfriend['name']),
            trailing: ElevatedButton(
              onPressed: () async {
                await _apiService.approveFriendRequest(unfriend['friendRequestId']);
                fetchData();
              },
              child: Text('Approve!'),
            ),
          );
        },
      ),
    );
  }
}
