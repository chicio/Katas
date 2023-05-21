//
//  SendBirthdateGreetingService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

enum SendBirthdateGreetingServiceError: Error {
    case SendFailed
}

protocol SendBirthdateGreetingService {
    func send(friend: Friend) throws
}
