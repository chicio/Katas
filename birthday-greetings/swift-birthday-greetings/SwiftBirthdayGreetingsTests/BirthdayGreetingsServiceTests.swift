//
//  swift_birthday_greetingsTests.swift
//  swift-birthday-greetingsTests
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import XCTest
import Cuckoo
@testable import SwiftBirthdayGreetings

extension Filename: Matchable {
    public var matcher: Cuckoo.ParameterMatcher<SwiftBirthdayGreetings.Filename> {
        return ParameterMatcher { tested in
            return tested.self.value == self.value
        }
    }
    
    public typealias MatchedType = Filename
    
}

class BirthdayGreetingsServiceTests: XCTestCase {
    
    func testExample() throws {
        
        let friendListReaderMock = MockFriendsListReader()
        
        stub(friendListReaderMock) { stub in
            when(stub.get(file: Filename(value: "friends.csv"))).then { filename in
                [
                    Friend(
                        firstName: FirstName(value: "fabrizio"),
                        lastName: LastName(value: "duroni"),
                        birthdate: Date(),
                        email: Email(value: "")
                    )
                ]
            }
        }
        
        let service = BirthdayGreetingsService()
    }
    
    
}
