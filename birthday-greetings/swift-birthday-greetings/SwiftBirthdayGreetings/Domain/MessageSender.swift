//
//  MessageSender.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

enum MessageSenderError: Error {
    case SendFailed
}

protocol MessageSender  {
    func send(message: Message) throws
}
