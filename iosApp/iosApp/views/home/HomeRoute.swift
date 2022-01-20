//
//  HomeRoute.swift
//  iosApp
//
//  Created by Sergio Casero Hernández on 20/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import HomeUI

class HomeProxy: ObservableObject {
    var viewModel = HomeViewModel()
    
    @Published var state: HomeState = HomeState.List()
    
    init() {
        viewModel.observe(viewModel.state) { newState in
             self.state = newState as! HomeState
        }
    }
}

struct HomeRoute: View {
    
    @ObservedObject var proxy = HomeProxy()

    var body: some View {
        HomeContent(state: proxy.state, onEvent: { event in
            proxy.viewModel.onEvent(event: event)
        })
            .onAppear(perform: {
                proxy.viewModel.onEvent(event: HomeEvent.Attach())
            }).onDisappear(perform: {
                proxy.viewModel.detach()
            })

    }
}

struct HomeContent: View {

    var state: HomeState
    var onEvent: (HomeEvent) -> Void

    var body: some View {
        
        VStack {
            if state is HomeState.List {
                Text("List")
            } else {
                Text("Map")
            }
            
            HStack {
                Button(action: {
                    onEvent(HomeEvent.List())
                }) {
                    Text("List")
                }
                Button(action: {
                    onEvent(HomeEvent.Map())
                }) {
                    Text("Map")
                }
            }
        }
    }
}
