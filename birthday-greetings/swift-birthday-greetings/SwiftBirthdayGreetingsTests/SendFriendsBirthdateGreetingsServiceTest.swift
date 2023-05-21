//
//  SendFriendsBirthdateGreetingsServiceTest.swift
//  SwiftBirthdayGreetingsTests
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import XCTest
import Cuckoo
@testable import SwiftBirthdayGreetings

final class SendFriendsBirthdateGreetingsServiceTest: XCTestCase {
    private let firstFriend = Friend(
        firstName: FirstName(value: "fabrizio"),
        lastName: LastName(value: "duroni"),
        birthdate: Date(),
        email: Email(value: "d@b.c")
    )
    private let secondFriend = Friend(
        firstName: FirstName(value: "chiara"),
        lastName: LastName(value: "polito"),
        birthdate: Date(),
        email: Email(value: "a@b.c")
    )
    
    func testSendSuccess() throws {
        let sendBirthdateGreetingServiceMock = MockSendBirthdateGreetingService()
        
        stub(sendBirthdateGreetingServiceMock) { stub in
            when(stub.send(friend: any())).thenDoNothing()
        }
        
        let sendFriendsBirthdateGreetingsService = SendFriendsBirthdateGreetingsService(sendBirthdateGreetingService: sendBirthdateGreetingServiceMock)
        
        try sendFriendsBirthdateGreetingsService.send(friends: [firstFriend, secondFriend])
        
        verify(sendBirthdateGreetingServiceMock).send(friend: equal(to: firstFriend))
        verify(sendBirthdateGreetingServiceMock).send(friend: equal(to: secondFriend))
    }
    
    func testSendFails() throws {
        let sendBirthdateGreetingServiceMock = MockSendBirthdateGreetingService()
        
        stub(sendBirthdateGreetingServiceMock) { stub in
            when(stub.send(friend: any())).thenThrow(SendBirthdateGreetingServiceError.SendFailed)
        }
        
        let sendFriendsBirthdateGreetingsService = SendFriendsBirthdateGreetingsService(sendBirthdateGreetingService: sendBirthdateGreetingServiceMock)
        
        XCTAssertThrowsError(try sendFriendsBirthdateGreetingsService.send(friends: [firstFriend, secondFriend]))
        
        verify(sendBirthdateGreetingServiceMock).send(friend: equal(to: firstFriend))
        verifyNoMoreInteractions(sendBirthdateGreetingServiceMock)
    }
}
