//
//  FoundationTodayDateRetriever.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Foundation

class FoundationTodayDateRetriever: TodayDateRetriever {
    func get() -> Date {
        return Date()
    }
}
