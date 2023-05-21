//
//  SendFriendBirthdateGreetingService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

class SendFriendBirthdateGreetingService: SendBirthdateGreetingService {
    private let greetingsMessageSender: GreetingsMessageSender
    private let todayDateRetriever: TodayDateRetriever
    
    init(
        greetingsMessageSender: GreetingsMessageSender,
        todayDateRetriever: TodayDateRetriever
    ) {
        self.greetingsMessageSender = greetingsMessageSender
        self.todayDateRetriever = todayDateRetriever
    }
    
    func send(friend: Friend) throws {
        if(friend.birthdate == todayDateRetriever.get()) {
            try greetingsMessageSender.send(for: friend)
        }
    }
}
