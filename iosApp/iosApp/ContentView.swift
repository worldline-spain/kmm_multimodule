import SwiftUI
import PoiUI

struct ContentView: View {

  @ObservedObject var observable = ObservableViewModel<PoiListViewModel, PoiListState>(viewModel: PoiListViewModel())

  var body: some View {
    let viewState = observable.state
    PoiListScreen(state: viewState)
      .onAppear(perform: {
        observable.viewModel.attach()
      }).onDisappear(perform: {
        observable.viewModel.detach()
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
    ContentView()
  }
}
