//
//  CSVFriendsListReader.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Foundation

class CSVFriendsListReader: FriendsListReader {
    private let dateFormatter = DateFormatter()
    private let header = "last_name, first_name, date_of_birth, email"
    
    init() {
        dateFormatter.dateFormat = "yyyy/MM/dd"
    }
    
    func get(file: Filename) throws -> Friends {
        guard let content = try? String(contentsOfFile: file.value) else {
            throw FriendsListReaderError.InvalidFilename
        }
        
        return try content
            .components(separatedBy: "\n")
            .filter { $0 != header || $0 == "" }
            .map{ $0.components(separatedBy: ",") }
            .dropLast(1)
            .map({ data in
                guard let firstNameString = data[safe: 1] else {
                    throw FriendsListReaderError.InvalidFormat
                }
                
                guard let lastNameString = data[safe: 0] else {
                    throw FriendsListReaderError.InvalidFormat
                }
                
                guard let dateString = data[safe: 2],
                        let birthdate = dateFormatter.date(from: dateString.trimmingCharacters(in: .whitespacesAndNewlines)) else {
                    throw FriendsListReaderError.InvalidFormat
                }

                guard let emailString = data[safe: 3] else {
                    throw FriendsListReaderError.InvalidFormat
                }
                
                return Friend(
                    firstName: FirstName(value: firstNameString.trimmingCharacters(in: .whitespacesAndNewlines)),
                    lastName: LastName(value: lastNameString.trimmingCharacters(in: .whitespacesAndNewlines)),
                    birthdate: birthdate,
                    email: Email(value: emailString.trimmingCharacters(in: .whitespacesAndNewlines))
                )
            })
    }
}
