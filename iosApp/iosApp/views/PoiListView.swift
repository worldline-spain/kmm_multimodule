import SwiftUI
import PoiUI

class PoiListProxy: ObservableObject {
    var viewModel = PoiListViewModel()
    
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
      PoiListScreen(state: proxy.state)
      .onAppear(perform: {
          proxy.viewModel.onEvent(event: PoiListEvent.Attach())
      }).onDisappear(perform: {
          proxy.viewModel.detach()
      })

  }
}

struct PoiListScreen: View {

  var state: PoiListState

  var body: some View {
    if state is PoiListState.InProgress {
      ProgressView()
    } else if state is PoiListState.Error {
      EmptyView()
    } else if state is PoiListState.Success {
      if let response = state as? PoiListState.Success {
          PoiListContent(pois: response.pois)
      }
    }
  }
}

struct PoiListContent: View {
    
    var pois: [CorePoi] = []
    
    init(pois: [CorePoi]) {
        self.pois.removeAll()
        self.pois.append(contentsOf: pois)
    }
    
    var body: some View {
        List {
          ForEach(pois, id: \.self.id) { poi in
            HStack {
              Text(poi.title)
            }.padding()
          }
        }
    }
}

struct ContentView_Previews: PreviewProvider {

  static var previews: some View {
      PoiListView()
  }
}
