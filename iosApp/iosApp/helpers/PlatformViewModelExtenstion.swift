//
//  PlatformViewModelExtenstion.swift
//  iosApp
//
//  Created by Pilar García on 10/1/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension PlatformViewModel : ObservableObject {
    
}

public extension ObservableObject where Self: PlatformViewModel {

    func state<T, R>(
        _ flowKey: KeyPath<Self, CStateFlow<T>>,
        equals: @escaping (T?, T?) -> Bool,
        mapper: @escaping (T) -> R
    ) -> R {
        let stateFlow: CStateFlow<T> = self[keyPath: flowKey]
        var lastValue: T? = stateFlow.value
        
        var disposable: DisposableHandle? = nil
        
        disposable = stateFlow.subscribe(onCollect: { value in
            if !equals(lastValue, value) {
                lastValue = value
                self.objectWillChange.send()
                disposable?.dispose()
            }
        })
        
        return mapper(stateFlow.value!)
    }
}
