//
//  Console.swift
//  swift-bow-tennis-game
//
//  Created by Fabrizio Duroni on 08.09.20.
//  Copyright © 2020 Fabrizio Duroni. All rights reserved.
//

import Foundation
import BowEffects

func puts(message: String) -> IO<Never, Void> {
    return IO.invoke { print(message) }
}

func reads() -> IO<Never, String> {
    return IO.invoke { readLine()! }
}

func ask(question: String) -> IO<Never, String> {
    return puts(message: question).flatMap(reads)^
}
