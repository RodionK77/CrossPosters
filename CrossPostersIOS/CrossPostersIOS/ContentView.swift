//
//  ContentView.swift
//  CrossPostersIOS
//
//  Created by Родион Каширин on 17.09.2024.
//

import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = PostersViewModel()

    var body: some View {
        NavigationView {
            ScrollView {
                VStack(alignment: .center, spacing: 8) {
                    ForEach(viewModel.posters.docs, id: \.poster?.url) { item in
                        if let urlString = item.poster?.url, let url = URL(string: urlString) {
                            AsyncImage(url: url) { image in
                                image.resizable()
                                    .aspectRatio(contentMode: .fit)
                            } placeholder: {
                                Image(systemName: "photo")
                                    .resizable()
                                    .aspectRatio(contentMode: .fit)
                            }
                            .padding(8)
                            .frame(width: 300, height: 300)
                        } else {
                            Image(systemName: "photo")
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .padding(8)
                                .frame(width: 300, height: 300)
                        }
                    }
                }
                .padding()
            }
            .navigationTitle("Posters")
        }
        .onAppear {
            viewModel.fetchPosters()
        }
    }
}
