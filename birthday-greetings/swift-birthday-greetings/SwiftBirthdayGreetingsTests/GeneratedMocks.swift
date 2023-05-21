// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/FriendsListReader.swift at 2023-05-21 10:23:24 +0000

//
//  FriendsListReader.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Cuckoo
@testable import SwiftBirthdayGreetings

import Foundation






 class MockFriendsListReader: FriendsListReader, Cuckoo.ProtocolMock {
    
     typealias MocksType = FriendsListReader
    
     typealias Stubbing = __StubbingProxy_FriendsListReader
     typealias Verification = __VerificationProxy_FriendsListReader

     let cuckoo_manager = Cuckoo.MockManager.preconfiguredManager ?? Cuckoo.MockManager(hasParent: false)

    
    private var __defaultImplStub: FriendsListReader?

     func enableDefaultImplementation(_ stub: FriendsListReader) {
        __defaultImplStub = stub
        cuckoo_manager.enableDefaultStubImplementation()
    }
    

    

    

    
    
    
    
     func get(file: Filename) throws -> Friends {
        
    return try cuckoo_manager.callThrows(
    """
    get(file: Filename) throws -> Friends
    """,
            parameters: (file),
            escapingParameters: (file),
            superclassCall:
                
                Cuckoo.MockManager.crashOnProtocolSuperclassCall()
                ,
            defaultCall: __defaultImplStub!.get(file: file))
        
    }
    
    

     struct __StubbingProxy_FriendsListReader: Cuckoo.StubbingProxy {
        private let cuckoo_manager: Cuckoo.MockManager
    
         init(manager: Cuckoo.MockManager) {
            self.cuckoo_manager = manager
        }
        
        
        
        
        func get<M1: Cuckoo.Matchable>(file: M1) -> Cuckoo.ProtocolStubThrowingFunction<(Filename), Friends> where M1.MatchedType == Filename {
            let matchers: [Cuckoo.ParameterMatcher<(Filename)>] = [wrap(matchable: file) { $0 }]
            return .init(stub: cuckoo_manager.createStub(for: MockFriendsListReader.self, method:
    """
    get(file: Filename) throws -> Friends
    """, parameterMatchers: matchers))
        }
        
        
    }

     struct __VerificationProxy_FriendsListReader: Cuckoo.VerificationProxy {
        private let cuckoo_manager: Cuckoo.MockManager
        private let callMatcher: Cuckoo.CallMatcher
        private let sourceLocation: Cuckoo.SourceLocation
    
         init(manager: Cuckoo.MockManager, callMatcher: Cuckoo.CallMatcher, sourceLocation: Cuckoo.SourceLocation) {
            self.cuckoo_manager = manager
            self.callMatcher = callMatcher
            self.sourceLocation = sourceLocation
        }
    
        
    
        
        
        
        @discardableResult
        func get<M1: Cuckoo.Matchable>(file: M1) -> Cuckoo.__DoNotUse<(Filename), Friends> where M1.MatchedType == Filename {
            let matchers: [Cuckoo.ParameterMatcher<(Filename)>] = [wrap(matchable: file) { $0 }]
            return cuckoo_manager.verify(
    """
    get(file: Filename) throws -> Friends
    """, callMatcher: callMatcher, parameterMatchers: matchers, sourceLocation: sourceLocation)
        }
        
        
    }
}


 class FriendsListReaderStub: FriendsListReader {
    

    

    
    
    
    
     func get(file: Filename) throws -> Friends  {
        return DefaultValueRegistry.defaultValue(for: (Friends).self)
    }
    
    
}





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/SendBirthdateGreetingService.swift at 2023-05-21 10:23:24 +0000

//
//  SendBirthdateGreetingService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Cuckoo
@testable import SwiftBirthdayGreetings

import Foundation






 class MockSendBirthdateGreetingService: SendBirthdateGreetingService, Cuckoo.ProtocolMock {
    
     typealias MocksType = SendBirthdateGreetingService
    
     typealias Stubbing = __StubbingProxy_SendBirthdateGreetingService
     typealias Verification = __VerificationProxy_SendBirthdateGreetingService

     let cuckoo_manager = Cuckoo.MockManager.preconfiguredManager ?? Cuckoo.MockManager(hasParent: false)

    
    private var __defaultImplStub: SendBirthdateGreetingService?

     func enableDefaultImplementation(_ stub: SendBirthdateGreetingService) {
        __defaultImplStub = stub
        cuckoo_manager.enableDefaultStubImplementation()
    }
    

    

    

    
    
    
    
     func send(friend: Friend) throws {
        
    return try cuckoo_manager.callThrows(
    """
    send(friend: Friend) throws
    """,
            parameters: (friend),
            escapingParameters: (friend),
            superclassCall:
                
                Cuckoo.MockManager.crashOnProtocolSuperclassCall()
                ,
            defaultCall: __defaultImplStub!.send(friend: friend))
        
    }
    
    

     struct __StubbingProxy_SendBirthdateGreetingService: Cuckoo.StubbingProxy {
        private let cuckoo_manager: Cuckoo.MockManager
    
         init(manager: Cuckoo.MockManager) {
            self.cuckoo_manager = manager
        }
        
        
        
        
        func send<M1: Cuckoo.Matchable>(friend: M1) -> Cuckoo.ProtocolStubNoReturnThrowingFunction<(Friend)> where M1.MatchedType == Friend {
            let matchers: [Cuckoo.ParameterMatcher<(Friend)>] = [wrap(matchable: friend) { $0 }]
            return .init(stub: cuckoo_manager.createStub(for: MockSendBirthdateGreetingService.self, method:
    """
    send(friend: Friend) throws
    """, parameterMatchers: matchers))
        }
        
        
    }

     struct __VerificationProxy_SendBirthdateGreetingService: Cuckoo.VerificationProxy {
        private let cuckoo_manager: Cuckoo.MockManager
        private let callMatcher: Cuckoo.CallMatcher
        private let sourceLocation: Cuckoo.SourceLocation
    
         init(manager: Cuckoo.MockManager, callMatcher: Cuckoo.CallMatcher, sourceLocation: Cuckoo.SourceLocation) {
            self.cuckoo_manager = manager
            self.callMatcher = callMatcher
            self.sourceLocation = sourceLocation
        }
    
        
    
        
        
        
        @discardableResult
        func send<M1: Cuckoo.Matchable>(friend: M1) -> Cuckoo.__DoNotUse<(Friend), Void> where M1.MatchedType == Friend {
            let matchers: [Cuckoo.ParameterMatcher<(Friend)>] = [wrap(matchable: friend) { $0 }]
            return cuckoo_manager.verify(
    """
    send(friend: Friend) throws
    """, callMatcher: callMatcher, parameterMatchers: matchers, sourceLocation: sourceLocation)
        }
        
        
    }
}


 class SendBirthdateGreetingServiceStub: SendBirthdateGreetingService {
    

    

    
    
    
    
     func send(friend: Friend) throws  {
        return DefaultValueRegistry.defaultValue(for: (Void).self)
    }
    
    
}





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/SendBirthdateGreetingsService.swift at 2023-05-21 10:23:24 +0000

//
//  SendBirthdateGreetingsService.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 20/05/23.
//

import Cuckoo
@testable import SwiftBirthdayGreetings

import Foundation






 class MockSendBirthdateGreetingsService: SendBirthdateGreetingsService, Cuckoo.ProtocolMock {
    
     typealias MocksType = SendBirthdateGreetingsService
    
     typealias Stubbing = __StubbingProxy_SendBirthdateGreetingsService
     typealias Verification = __VerificationProxy_SendBirthdateGreetingsService

     let cuckoo_manager = Cuckoo.MockManager.preconfiguredManager ?? Cuckoo.MockManager(hasParent: false)

    
    private var __defaultImplStub: SendBirthdateGreetingsService?

     func enableDefaultImplementation(_ stub: SendBirthdateGreetingsService) {
        __defaultImplStub = stub
        cuckoo_manager.enableDefaultStubImplementation()
    }
    

    

    

    
    
    
    
     func send(friends: Friends) throws {
        
    return try cuckoo_manager.callThrows(
    """
    send(friends: Friends) throws
    """,
            parameters: (friends),
            escapingParameters: (friends),
            superclassCall:
                
                Cuckoo.MockManager.crashOnProtocolSuperclassCall()
                ,
            defaultCall: __defaultImplStub!.send(friends: friends))
        
    }
    
    

     struct __StubbingProxy_SendBirthdateGreetingsService: Cuckoo.StubbingProxy {
        private let cuckoo_manager: Cuckoo.MockManager
    
         init(manager: Cuckoo.MockManager) {
            self.cuckoo_manager = manager
        }
        
        
        
        
        func send<M1: Cuckoo.Matchable>(friends: M1) -> Cuckoo.ProtocolStubNoReturnThrowingFunction<(Friends)> where M1.MatchedType == Friends {
            let matchers: [Cuckoo.ParameterMatcher<(Friends)>] = [wrap(matchable: friends) { $0 }]
            return .init(stub: cuckoo_manager.createStub(for: MockSendBirthdateGreetingsService.self, method:
    """
    send(friends: Friends) throws
    """, parameterMatchers: matchers))
        }
        
        
    }

     struct __VerificationProxy_SendBirthdateGreetingsService: Cuckoo.VerificationProxy {
        private let cuckoo_manager: Cuckoo.MockManager
        private let callMatcher: Cuckoo.CallMatcher
        private let sourceLocation: Cuckoo.SourceLocation
    
         init(manager: Cuckoo.MockManager, callMatcher: Cuckoo.CallMatcher, sourceLocation: Cuckoo.SourceLocation) {
            self.cuckoo_manager = manager
            self.callMatcher = callMatcher
            self.sourceLocation = sourceLocation
        }
    
        
    
        
        
        
        @discardableResult
        func send<M1: Cuckoo.Matchable>(friends: M1) -> Cuckoo.__DoNotUse<(Friends), Void> where M1.MatchedType == Friends {
            let matchers: [Cuckoo.ParameterMatcher<(Friends)>] = [wrap(matchable: friends) { $0 }]
            return cuckoo_manager.verify(
    """
    send(friends: Friends) throws
    """, callMatcher: callMatcher, parameterMatchers: matchers, sourceLocation: sourceLocation)
        }
        
        
    }
}


 class SendBirthdateGreetingsServiceStub: SendBirthdateGreetingsService {
    

    

    
    
    
    
     func send(friends: Friends) throws  {
        return DefaultValueRegistry.defaultValue(for: (Void).self)
    }
    
    
}




