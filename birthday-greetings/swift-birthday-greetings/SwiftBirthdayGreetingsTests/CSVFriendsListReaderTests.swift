//
//  CSVFriendsListReaderTests.swift
//  SwiftBirthdayGreetingsTests
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import XCTest
import Foundation
@testable import SwiftBirthdayGreetings

final class CSVFriendsListReaderTests: XCTestCase {
    private let dateFormatter = DateFormatter()

    func testSuccess() throws {
        dateFormatter.dateFormat = "yyyy/MM/dd"
        
        let friends = try CSVFriendsListReader().get(file: Filename(value: filePath(name: "success")))
        
        XCTAssertEqual(
            friends,
            [
                Friend(
                    firstName: FirstName(value: "John"),
                    lastName: LastName(value: "Doe"),
                    birthdate: dateFormatter.date(from: "1982/10/08")!,
                    email: Email(value: "john.doe@foobar.com")
                ),
                Friend(
                    firstName: FirstName(value: "Mary"),
                    lastName: LastName(value: "Ann"),
                    birthdate: dateFormatter.date(from: "1975/09/11")!,
                    email: Email(value: "mary.ann@foobar.com")
                ),

            ]
        )
    }
    
    func testInvalidFile() throws {
        XCTAssertThrowsError(try CSVFriendsListReader().get(file: Filename(value: "not-exists")))
    }
    
    func testInvalidFormat() throws {
        XCTAssertThrowsError(try CSVFriendsListReader().get(file: Filename(value: filePath(name: "invalid-row"))))
        XCTAssertThrowsError(try CSVFriendsListReader().get(file: Filename(value: filePath(name: "invalid-date"))))
    }
    
    private func filePath(name: String) -> String {
        return Bundle(for: type(of: self)).path(forResource: name, ofType: "csv")!
    }
}
