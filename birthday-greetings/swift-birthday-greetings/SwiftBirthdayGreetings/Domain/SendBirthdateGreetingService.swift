//
//  SendBirthdateGreetingService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

protocol SendBirthdateGreetingService {
    func send(friend: Friend) throws
}
