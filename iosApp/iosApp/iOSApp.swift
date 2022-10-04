import SwiftUI
import PoiUI
import HomeUI
import core

class NavigationProxy: ObservableObject {
    
    var viewModel : NavigationViewModel
    
    @Published var state: NavigationState = NavigationState.Home()
    
    init() {
        viewModel = NavigationViewModel()
        viewModel.observe(viewModel.state) { newState in
            self.state = newState as! NavigationState
        }
    }
    
}


@main
struct iOSApp: App {
    
    @ObservedObject var proxy : NavigationProxy
    
    init() {
        
        HomeUiModule().initiOS()
        PoiUiModule().initiOS()
        proxy = NavigationProxy()
    }
    
    var body: some Scene {
        WindowGroup {
            getDestination(proxy: proxy)
        }
    }
    
    func getDestination(proxy: NavigationProxy) -> AnyView {
        if proxy.state is NavigationState.Home {
            return AnyView(HomeRoute { navigationEvent in
                proxy.viewModel.onEvent(screen: navigationEvent)
            })
        } else {
            return AnyView(PoiDetailView())
        }
    }
}
