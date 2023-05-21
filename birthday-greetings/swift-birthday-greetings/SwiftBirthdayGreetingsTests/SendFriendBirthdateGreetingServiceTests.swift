//
//  SendFriendBirthdateGreetingServiceTests.swift
//  SwiftBirthdayGreetingsTests
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation
import XCTest
import Cuckoo
@testable import SwiftBirthdayGreetings

final class SendFriendBirthdateGreetingServiceTests: XCTestCase {
    
    func testSuccess() throws {
        let matchingDate = Date()
        let friend = Friend(
            firstName: FirstName(value: "fabrizio"),
            lastName: LastName(value: "duroni"),
            birthdate: matchingDate,
            email: Email(value: "d@b.c")
        )
        let greetingsMessageSenderMock = MockGreetingsMessageSender()
        let todayDateRetrieverMock = MockTodayDateRetriever()
        
        stub(todayDateRetrieverMock) { stub in
            when(stub.get()).thenReturn(matchingDate)
        }
        
        stub(greetingsMessageSenderMock) { stub in
            when(stub.send(for: any())).thenDoNothing()
        }
        
        try SendFriendBirthdateGreetingService(
            greetingsMessageSender: greetingsMessageSenderMock,
            todayDateRetriever: todayDateRetrieverMock
        ).send(friend: friend)
        
        verify(greetingsMessageSenderMock).send(for: equal(to: friend))
    }
    
    func testDateNotMatching() throws {
        let friend = Friend(
            firstName: FirstName(value: "fabrizio"),
            lastName: LastName(value: "duroni"),
            birthdate: Date(timeIntervalSince1970: 1),
            email: Email(value: "d@b.c")
        )
        let greetingsMessageSenderMock = MockGreetingsMessageSender()
        let todayDateRetrieverMock = MockTodayDateRetriever()
        
        stub(todayDateRetrieverMock) { stub in
            when(stub.get()).thenReturn(Date())
        }
        
        try SendFriendBirthdateGreetingService(
            greetingsMessageSender: greetingsMessageSenderMock,
            todayDateRetriever: todayDateRetrieverMock
        ).send(friend: friend)
        
        verifyNoMoreInteractions(greetingsMessageSenderMock)
    }
    
    func testSenderError() throws {
        let matchingDate = Date()
        let friend = Friend(
            firstName: FirstName(value: "fabrizio"),
            lastName: LastName(value: "duroni"),
            birthdate: matchingDate,
            email: Email(value: "d@b.c")
        )
        let greetingsMessageSenderMock = MockGreetingsMessageSender()
        let todayDateRetrieverMock = MockTodayDateRetriever()
        
        stub(todayDateRetrieverMock) { stub in
            when(stub.get()).thenReturn(matchingDate)
        }
        
        stub(greetingsMessageSenderMock) { stub in
            when(stub.send(for: any())).thenThrow(GreetingsMessageSenderError.SendFailure)
        }
        
        XCTAssertThrowsError(
            try SendFriendBirthdateGreetingService(
                greetingsMessageSender: greetingsMessageSenderMock,
                todayDateRetriever: todayDateRetrieverMock
            ).send(friend: friend)
        )
        
        verify(greetingsMessageSenderMock).send(for: equal(to: friend))
    }
}
