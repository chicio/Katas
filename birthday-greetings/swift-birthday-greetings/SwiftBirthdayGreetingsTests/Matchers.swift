//
//  Matchers.swift
//  SwiftBirthdayGreetingsTests
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Foundation
import Cuckoo
@testable import SwiftBirthdayGreetings

func equal(to value: Filename) -> ParameterMatcher<Filename> {
  return ParameterMatcher { tested in
      return tested.value == value.value
  }
}

func equal(to value: Friend) -> ParameterMatcher<Friend> {
  return ParameterMatcher { tested in
      return tested == value
  }
}

