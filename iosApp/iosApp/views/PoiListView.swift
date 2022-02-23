import SwiftUI
import PoiUI

class PoiListProxy: ObservableObject {
    
    var viewModel = PoiListViewModel(onNavigationEvent: { navigationEvent in
        print("Event: \(navigationEvent)")
    })
    
    @Published var state: PoiListState = PoiListState.InProgress()
    
    init() {
        viewModel.observe(viewModel.state) { newState in
            self.state = newState as! PoiListState
        }
    }
}

struct PoiListView: View {
    
    @ObservedObject var proxy = PoiListProxy()
    
    var body: some View {
        PoiListScreen(state: proxy.state, onEvent: {event in proxy.viewModel.onEvent(event: event) })
            .onAppear(perform: {
                proxy.viewModel.onEvent(event: PoiListEvent.Attach())
            }).onDisappear(perform: {
                proxy.viewModel.detach()
            })
        
    }
}

struct PoiListScreen: View {
    
    var state: PoiListState
    var onEvent: (PoiListEvent) -> Void
    
    var body: some View {
        if state is PoiListState.InProgress {
            ProgressView()
        } else if state is PoiListState.Error {
            EmptyView()
        } else if state is PoiListState.Success {
            if let response = state as? PoiListState.Success {
                PoiListContent(pois: response.pois, onEvent: onEvent)
            }
        }
    }
}

struct PoiListContent: View {
    
    var pois: [CorePoi] = []
    var onEvent: (PoiListEvent) -> Void
    
    init(pois: [CorePoi], onEvent: @escaping (PoiListEvent) -> Void) {
        self.pois.removeAll()
        self.pois.append(contentsOf: pois)
        self.onEvent = onEvent
    }
    
    var body: some View {
        List {
            ForEach(pois, id: \.self.id) { poi in
                HStack {
                    Text(poi.title)
                    Spacer()
                }.padding()
                .contentShape(Rectangle())
                .onTapGesture {
                    self.onEvent(PoiListEvent.OnItemClick(poi: poi))
                }
            }
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
    
    static var previews: some View {
        PoiListContent(pois: [CorePoi(id: 1, title: "Title", latitude: 0.0, longitude: 0.0, image: "")]) { PoiListEvent in
        }
    }
}
