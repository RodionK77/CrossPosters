import 'package:cross_posters_flutter/Tokens.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:cached_network_image/cached_network_image.dart';

void main() {
  runApp(const MyApp());
}


class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => PostersViewModel(), // Инициализация ViewModel
      child: MaterialApp(
        title: 'Posters',
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        home: const MainScreen(), // MainScreen находится внутри провайдера
      ),
    );
  }
}

class MainScreen extends StatelessWidget {

  const MainScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<PostersViewModel>(context);
    final posters = viewModel.posters;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Posters', style: TextStyle(
          fontSize: 32,
          fontWeight: FontWeight.bold,
        ),),
      ),
      body: Padding(
        padding: const EdgeInsets.only(left: 64, right: 64, top: 8.0, bottom: 8.0),
        child: posters.isEmpty
            ? const Center(child: CircularProgressIndicator())
            : ListView.separated(
          itemCount: posters.length,
          separatorBuilder: (context, index) => const SizedBox(height: 8.0),
          itemBuilder: (context, index) {
            final item = posters[index];
            return CachedNetworkImage(
              imageUrl: item.poster?.url ?? '',
              placeholder: (context, url) =>
                  Image.asset('assets/poster_placeholder.jpg'),
              errorWidget: (context, url, error) =>
                  Image.asset('assets/poster_placeholder.jpg'),
              fit: BoxFit.cover,
              width: 10,
              height: 450,
            );
          },
        ),
      ),
    );
  }
}

class PostersViewModel extends ChangeNotifier {
  List<PosterInfo> posters = [];
  final String apiUrl =
      'https://api.kinopoisk.dev/v1.4/movie?page=1&limit=120&selectFields=poster&type=movie&rating.kp=8.0-10&votes.kp=1000000-50000000&lists=top250&token=${Tokens.TOKEN1}';

  PostersViewModel() {
    fetchPosters();
  }

  Future<void> fetchPosters() async {
    try {
      final response = await http.get(Uri.parse(apiUrl));
      if (response.statusCode == 200) {
        final data = json.decode(response.body);
        posters = (data['docs'] as List)
            .map((item) => PosterInfo.fromJson(item))
            .toList();
        notifyListeners();
      }
    } catch (e) {
      print("Error fetching posters: $e");
    }
  }
}

class PosterInfo {
  final Poster? poster;

  PosterInfo({this.poster});

  factory PosterInfo.fromJson(Map<String, dynamic> json) {
    return PosterInfo(
      poster: json['poster'] != null
          ? Poster.fromJson(json['poster'])
          : null,
    );
  }
}

class Poster {
  final String? url;
  final String? previewUrl;

  Poster({this.url, this.previewUrl});

  factory Poster.fromJson(Map<String, dynamic> json) {
    return Poster(
      url: json['url'],
      previewUrl: json['previewUrl'],
    );
  }
}



