//
//  GreetingsMessageSender.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

enum GreetingsMessageSenderError: Error {
    case SendFailure
}

protocol GreetingsMessageSender {
    func send(for friend: Friend) throws
}
