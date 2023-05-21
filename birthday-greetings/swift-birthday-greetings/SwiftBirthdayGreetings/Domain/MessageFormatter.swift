//
//  MessageFormatter.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

protocol MessageFormatter {
    func format(friend: Friend) -> Message
}
