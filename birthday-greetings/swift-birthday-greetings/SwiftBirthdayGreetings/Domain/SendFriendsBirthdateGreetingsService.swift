//
//  SendFriendsBirthdateGreetingsService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

class SendFriendsBirthdateGreetingsService: SendBirthdateGreetingsService {
    private let sendBirthdateGreetingService: SendBirthdateGreetingService
    
    init(sendBirthdateGreetingService: SendBirthdateGreetingService) {
        self.sendBirthdateGreetingService = sendBirthdateGreetingService
    }
    
    func send(friends: Friends) throws {
        try friends.forEach { friend in
            try sendBirthdateGreetingService.send(friend: friend)
        }
    }
}
