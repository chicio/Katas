//
//  Friend.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Foundation

struct Email: Equatable {
    let value: String
}

struct FirstName: Equatable {
    let value: String
}

struct LastName: Equatable {
    let value: String
}

struct Friend: Equatable {
    let firstName: FirstName
    let lastName: LastName
    let birthdate: Date
    let email: Email
}

typealias Friends = [Friend]
