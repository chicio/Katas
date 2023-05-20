//
//  BirthdayGreetingsService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Foundation

class BirthdayGreetingsService {
    private let friendListReader: FriendsListReader
    private let sendBirthdateGreetingsService: SendBirthdateGreetingsService
    
    init(
        friendListReader: FriendsListReader,
        sendBirthdateGreetingsService: SendBirthdateGreetingsService
    ) {
        self.friendListReader = friendListReader
        self.sendBirthdateGreetingsService = sendBirthdateGreetingsService
    }
    
    func send(file: Filename) {
        sendBirthdateGreetingsService.send(
            friends: friendListReader.get(file: file)
        )
    }
}
