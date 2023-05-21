//
//  FormattedGreetingsMessageSenderTests.swift
//  SwiftBirthdayGreetingsTests
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation
import XCTest
import Cuckoo
@testable import SwiftBirthdayGreetings

final class FormattedGreetingsMessageSenderTests: XCTestCase {
    func testSuccess() throws {
        let friend = Friend(
            firstName: FirstName(value: "fabrizio"),
            lastName: LastName(value: "duroni"),
            birthdate: Date(),
            email: Email(value: "d@b.c")
        )
        let message = Message(
            subject: Subject(value: "a subject"),
            content: Content(value: "A content")
        )
        
        let messageSenderMock = MockMessageSender()
        let messageFormatterMock = MockMessageFormatter()
        
        stub(messageFormatterMock) { stub in
            when(stub.format(friend: any()))
                .thenReturn(message)
        }
        
        stub(messageSenderMock) { stub in
            when(stub.send(message: any())).thenDoNothing()
        }
        
        try FormattedGreetingsMessageSender(
            messageFormatter: messageFormatterMock,
            messageSender: messageSenderMock
        ).send(for: friend)
        
        verify(messageFormatterMock).format(friend: equal(to: friend))
        verify(messageSenderMock).send(message: equal(to: message))
    }
    
    func testFail() throws {
        let friend = Friend(
            firstName: FirstName(value: "fabrizio"),
            lastName: LastName(value: "duroni"),
            birthdate: Date(),
            email: Email(value: "d@b.c")
        )
        let message = Message(
            subject: Subject(value: "a subject"),
            content: Content(value: "A content")
        )
        
        let messageSenderMock = MockMessageSender()
        let messageFormatterMock = MockMessageFormatter()
        
        stub(messageFormatterMock) { stub in
            when(stub.format(friend: any()))
                .thenReturn(message)
        }
        
        stub(messageSenderMock) { stub in
            when(stub.send(message: any())).thenThrow(MessageSenderError.SendFailed)
        }
        
        XCTAssertThrowsError(
            try FormattedGreetingsMessageSender(
                messageFormatter: messageFormatterMock,
                messageSender: messageSenderMock
            ).send(for: friend)
        )
        
        verify(messageFormatterMock).format(friend: equal(to: friend))
        verify(messageSenderMock).send(message: equal(to: message))
    }
}
