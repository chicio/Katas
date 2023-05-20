//
//  SwiftBirthdayGreetingsTests.swift
//  SwiftBirthdayGreetingsTests
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import XCTest
import Cuckoo
@testable import SwiftBirthdayGreetings

final class SwiftBirthdayGreetingsTests: XCTestCase {
    private let friendListReaderMock = MockFriendsListReader()
    private let sendBirthdateGreetingsServiceMock = MockSendBirthdateGreetingsService()
    
    private let friends =  [
        Friend(
            firstName: FirstName(value: "fabrizio"),
            lastName: LastName(value: "duroni"),
            birthdate: Date(),
            email: Email(value: "d@b.c")
        ),
        Friend(
            firstName: FirstName(value: "chiara"),
            lastName: LastName(value: "polito"),
            birthdate: Date(),
            email: Email(value: "a@b.c")
        )
    ]

    func testSendSuccess() throws {
        stub(friendListReaderMock) { stub in
            when(stub.get(file: any())).then { _ in self.friends }
        }
        
        stub(sendBirthdateGreetingsServiceMock) { stub in
            when(stub.send(friends: any())).thenDoNothing()
        }
        
        try BirthdayGreetingsService(
            friendListReader: friendListReaderMock,
            sendBirthdateGreetingsService: sendBirthdateGreetingsServiceMock
        ).send(file: Filename(value: "friends.csv"))
        
        verify(friendListReaderMock).get(file: equal(to: Filename(value: "friends.csv")))
        verify(sendBirthdateGreetingsServiceMock).send(friends: equal(to: friends))
    }
    
    func testSendFailFriendsListReader() throws {
        stub(friendListReaderMock) { stub in
            when(stub.get(file: any())).thenThrow(FriendsListReaderError.InvalidFilename)
        }
        
        stub(sendBirthdateGreetingsServiceMock) { stub in
            when(stub.send(friends: any())).thenDoNothing()
        }
        
        XCTAssertThrowsError(
            try BirthdayGreetingsService(
                friendListReader: friendListReaderMock,
                sendBirthdateGreetingsService: sendBirthdateGreetingsServiceMock
            ).send(file: Filename(value: "friends.csv"))
        )
        
        verify(friendListReaderMock).get(file: equal(to: Filename(value: "friends.csv")))
        verifyNoMoreInteractions(sendBirthdateGreetingsServiceMock)
    }
}
