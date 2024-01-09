import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'Constants.dart';
class ApiService {
  final FlutterSecureStorage storage = FlutterSecureStorage();

  Future<String?> getJwtToken() async {
    try {
      final jwtToken = await storage.read(key: 'jwt_token');
      return jwtToken;
    } catch (error) {
      return "NOT_FOUND";
    }
  }

  Future<void> saveJwtToken(String token) async {
    await storage.write(key: 'jwt_token', value: token);
  }

  Future<void> logout() async {
    await storage.delete(key: 'jwt_token');
  }

  Future<bool> loginRequest(dynamic body) async {
      final response = await http.post(
        Uri.parse('${Constants.apiBaseUrl}/api/login'),
        headers: {
          'Content-Type': 'application/json',
        },
        body: jsonEncode(body),
      );
      Map<String, dynamic> jsonResponse = jsonDecode(response.body);
      if (response.statusCode == 200) {
        saveJwtToken(jsonResponse['jwt']);
        return true;
      }
      return false;
    }
    Future<bool> registerRequest(dynamic body) async {
      final response = await http.post(
        Uri.parse('${Constants.apiBaseUrl}/api/register'),
        headers: {
          'Content-Type': 'application/json',
        },
        body: jsonEncode(body),
      );
      if (response.statusCode == 200) {
        return true;
      }
      return false;
    }

  Future<List<dynamic>> getUnfriends() async {
    final jwtToken = await getJwtToken();
    if (jwtToken != null) {
      final response = await http.get(
        Uri.parse('${Constants.apiBaseUrl}/api/user/getunfriends'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $jwtToken',
        },
      );

      if (response.statusCode == 200) {
        final Map<String, dynamic> data = json.decode(response.body);
        return data['userDTOList'];
      } else {
        throw Exception('Failed to load data');
      }
    } else {
      throw Exception('JWT Token not found');
    }
  }

  Future<List<dynamic>> getFriendRequestList() async {
    final jwtToken = await getJwtToken();
    if (jwtToken != null) {
      final response = await http.get(
        Uri.parse('${Constants.apiBaseUrl}/api/user/getFriendRequestList'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $jwtToken',
        },
      );

      if (response.statusCode == 200) {
        final Map<String, dynamic> data = json.decode(response.body);
        print("ok");
        print(data);
        return data['userDTOList'];
      } else {
        throw Exception('Failed to load data');
      }
    } else {
      throw Exception('JWT Token not found');
    }
  }
  Future<List<dynamic>> getFriends() async {
    final jwtToken = await getJwtToken();
    if (jwtToken != null) {
      final response = await http.get(
        Uri.parse('${Constants.apiBaseUrl}/api/user/getfriends'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $jwtToken',
        },
      );

      if (response.statusCode == 200) {
        final Map<String, dynamic> data = json.decode(response.body);
        return data['userDTOList'];
      } else {
        throw Exception('Failed to load data');
      }
    } else {
      throw Exception('JWT Token not found');
    }
  }
  Future<void> addFriend(int id) async {
    final jwtToken = await getJwtToken();
    if (jwtToken != null) {
      final response = await http.post(
        Uri.parse('${Constants.apiBaseUrl}/api/user/addFriend?id=$id'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $jwtToken',
        },
      );

    }
  }
  Future<void> approveFriendRequest(int id) async {
    final jwtToken = await getJwtToken();
    if (jwtToken != null) {
      final response = await http.post(
        Uri.parse('${Constants.apiBaseUrl}/api/user/approveFriendRequest?id=$id'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $jwtToken',
        },
      );

    }
  }


  Future<bool> sendImage(String imagePath, int userId) async {
    try {
      final jwtToken = await getJwtToken();
      File imageFile = File(imagePath);
      if (!imageFile.existsSync()) {
        throw Exception("File doesn't exist");
      }

      final uri = Uri.parse('${Constants.apiBaseUrl}/api/user/sendImage');
      var request = http.MultipartRequest('POST', uri)
        ..fields['userid'] = userId.toString()
        ..files.add(await http.MultipartFile.fromPath('file', imageFile.path));
      request.headers['Authorization'] = 'Bearer '+jwtToken!;

      var response = await request.send();
      if (response.statusCode == 200) {
        return true;
      } else {
        return false;
      }
    } catch (e) {
      return false;
    }
  }

  Future<List<dynamic>> getImagelist() async {
    final jwtToken = await getJwtToken();
    if (jwtToken != null) {
      try {
        final response = await http.get(
          Uri.parse('${Constants.apiBaseUrl}/api/user/getImagelist'),
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer $jwtToken',
          },
        );
        final Map<String, dynamic>? responseData = json.decode(response.body);
        return responseData!['list'];

      } catch (e) {
        throw Exception('');
      }
    } else {
      throw Exception('JWT Token not found');
    }
  }

  Future<void> deletePhoto(int id) async {
    final jwtToken = await getJwtToken();
    if (jwtToken != null) {
      final response = await http.delete(
        Uri.parse('${Constants.apiBaseUrl}/api/deletePhoto?id=$id'),
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer $jwtToken',
        },
      );

    }
  }

}

