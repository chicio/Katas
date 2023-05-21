//
//  FormattedGreetingsMessageSender.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

class FormattedGreetingsMessageSender: GreetingsMessageSender {
    private let messageFormatter: MessageFormatter
    private let messageSender: MessageSender
    
    init(messageFormatter: MessageFormatter, messageSender: MessageSender) {
        self.messageFormatter = messageFormatter
        self.messageSender = messageSender
    }
    
    func send(for friend: Friend) throws {
        try messageSender.send(
            message: messageFormatter.format(friend: friend)
        )
    }
}
