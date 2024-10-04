//
//  PostersViewModel.swift
//  CrossPostersIOS
//
//  Created by Родион Каширин on 17.09.2024.
//

import Foundation
import Combine

class PostersViewModel: ObservableObject {
    @Published var posters = PosterItemResponse(docs: [])

    private var cancellables = Set<AnyCancellable>()
    private let apiUrl = "https://api.kinopoisk.dev/v1.4/movie?page=1&limit=120&selectFields=poster&type=movie&rating.kp=8.0-10&votes.kp=1000000-50000000&lists=top250&token=\(Tokens.TOKEN1)"

    func fetchPosters() {
        guard let url = URL(string: apiUrl) else { return }

        URLSession.shared.dataTaskPublisher(for: url)
            .map(\.data)
            .decode(type: PosterItemResponse.self, decoder: JSONDecoder())
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { completion in
                switch completion {
                case .finished:
                    break
                case .failure(let error):
                    print("Failed to fetch posters: \(error)")
                }
            }, receiveValue: { [weak self] response in
                self?.posters = response
            })
            .store(in: &cancellables)
    }
}
