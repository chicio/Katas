//
//  SendBirthdateGreetingsService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Foundation

protocol SendBirthdateGreetingsService {
    func send(friends: Friends) throws
}
