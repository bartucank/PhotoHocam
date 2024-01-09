import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:typed_data';
import 'package:flutter/material.dart';
import '../utils/ApiService.dart';
import 'package:photohocamui/pages/MessagePage.dart';

class ViewReceivedSnapPage extends StatelessWidget {
  final List<int> compressedImageData;
  final ApiService _apiService;
  final int imgid;

  const ViewReceivedSnapPage({
    Key? key,
    required this.compressedImageData,
    required ApiService apiService,
    required this.imgid,
  })  : _apiService = apiService,
        super(key: key);

  Future<void> deletePhoto(int imageId) async {
    try {
      await _apiService.deletePhoto(imageId);
    } catch (e) {
      print('Error deleting photo: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    List<int> decompressedBytes = ZLibCodec().decode(compressedImageData);
    return GestureDetector(
      onTap: () async {
        await deletePhoto(imgid);
        Navigator.pop(context);
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(
            builder: (context) => MessagesPage(),
          ),
        );
      },
      child: Scaffold(
        appBar: AppBar(title: Text('View Image')),
        body: Center(
          child: Image.memory(
            Uint8List.fromList(decompressedBytes),
            fit: BoxFit.cover,
          ),
        ),
      ),
    );
  }
}
