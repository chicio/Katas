//
//  main.swift
//  swift-bow-tennis-game
//
//  Created by Fabrizio Duroni on 08.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

import Foundation
import Bow

var a = Option.some("test")
var unfold = a.fold({ return "ciao" }) {
    value in
    return "value"
}

print("Hello, World!")

