import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';
import 'package:flutter/material.dart';
import '../utils/ApiService.dart';

class ViewReceivedSnapPage extends StatelessWidget {
  final List<int> compressedImageData;
  final ApiService _apiService;

  const ViewReceivedSnapPage({
    Key? key,
    required this.compressedImageData,
    required ApiService apiService,
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
      onTap: () {
        //deletePhoto();
        Navigator.pop(context);
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
