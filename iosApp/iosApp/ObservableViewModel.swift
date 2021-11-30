import PoiUI

class ObservableViewModel<
  ViewModel: RootViewModel,
  State: ViewState
>: ObservableObject {

  @Published private(set) var state: State

  let viewModel: ViewModel

  init(viewModel: ViewModel) {
    self.viewModel = viewModel
    self.state = State()

    viewModel.observe(
      viewModel.state,
      onChange: { state in
        self.state = state as! State
      })
  }

  deinit {
    viewModel.detach()
  }
}
