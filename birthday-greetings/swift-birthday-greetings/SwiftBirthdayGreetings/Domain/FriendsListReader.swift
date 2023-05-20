//
//  FriendsListReader.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Foundation

enum FriendsListReaderError: Error {
    case InvalidFilename
    case InvalidFormat
}

protocol FriendsListReader {
    func get(file: Filename) throws -> Friends
}
