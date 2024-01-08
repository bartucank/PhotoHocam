import 'dart:io';
import 'package:flutter/material.dart';
import 'FriendsForSnap.dart';
class ViewSnapPage extends StatelessWidget {
  final String imagePath;

  const ViewSnapPage({Key? key, required this.imagePath}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('View Snap')),
      body: Center(
        child: Image.file(
          File(imagePath),
          fit: BoxFit.cover,
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => FriendsForSnap(imagePath: imagePath),
            ),
          );
        },
        child: Icon(Icons.send),
      ),
    );
  }
}