import 'dart:convert';

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


}