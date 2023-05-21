// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/FriendsListReader.swift at 2023-05-21 11:07:49 +0000

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





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/GreetingsMessageSender.swift at 2023-05-21 11:07:49 +0000

//
//  GreetingsMessageSender.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Cuckoo
@testable import SwiftBirthdayGreetings

import Foundation






 class MockGreetingsMessageSender: GreetingsMessageSender, Cuckoo.ProtocolMock {
    
     typealias MocksType = GreetingsMessageSender
    
     typealias Stubbing = __StubbingProxy_GreetingsMessageSender
     typealias Verification = __VerificationProxy_GreetingsMessageSender

     let cuckoo_manager = Cuckoo.MockManager.preconfiguredManager ?? Cuckoo.MockManager(hasParent: false)

    
    private var __defaultImplStub: GreetingsMessageSender?

     func enableDefaultImplementation(_ stub: GreetingsMessageSender) {
        __defaultImplStub = stub
        cuckoo_manager.enableDefaultStubImplementation()
    }
    

    

    

    
    
    
    
     func send(for friend: Friend) throws {
        
    return try cuckoo_manager.callThrows(
    """
    send(for: Friend) throws
    """,
            parameters: (friend),
            escapingParameters: (friend),
            superclassCall:
                
                Cuckoo.MockManager.crashOnProtocolSuperclassCall()
                ,
            defaultCall: __defaultImplStub!.send(for: friend))
        
    }
    
    

     struct __StubbingProxy_GreetingsMessageSender: Cuckoo.StubbingProxy {
        private let cuckoo_manager: Cuckoo.MockManager
    
         init(manager: Cuckoo.MockManager) {
            self.cuckoo_manager = manager
        }
        
        
        
        
        func send<M1: Cuckoo.Matchable>(for friend: M1) -> Cuckoo.ProtocolStubNoReturnThrowingFunction<(Friend)> where M1.MatchedType == Friend {
            let matchers: [Cuckoo.ParameterMatcher<(Friend)>] = [wrap(matchable: friend) { $0 }]
            return .init(stub: cuckoo_manager.createStub(for: MockGreetingsMessageSender.self, method:
    """
    send(for: Friend) throws
    """, parameterMatchers: matchers))
        }
        
        
    }

     struct __VerificationProxy_GreetingsMessageSender: Cuckoo.VerificationProxy {
        private let cuckoo_manager: Cuckoo.MockManager
        private let callMatcher: Cuckoo.CallMatcher
        private let sourceLocation: Cuckoo.SourceLocation
    
         init(manager: Cuckoo.MockManager, callMatcher: Cuckoo.CallMatcher, sourceLocation: Cuckoo.SourceLocation) {
            self.cuckoo_manager = manager
            self.callMatcher = callMatcher
            self.sourceLocation = sourceLocation
        }
    
        
    
        
        
        
        @discardableResult
        func send<M1: Cuckoo.Matchable>(for friend: M1) -> Cuckoo.__DoNotUse<(Friend), Void> where M1.MatchedType == Friend {
            let matchers: [Cuckoo.ParameterMatcher<(Friend)>] = [wrap(matchable: friend) { $0 }]
            return cuckoo_manager.verify(
    """
    send(for: Friend) throws
    """, callMatcher: callMatcher, parameterMatchers: matchers, sourceLocation: sourceLocation)
        }
        
        
    }
}


 class GreetingsMessageSenderStub: GreetingsMessageSender {
    

    

    
    
    
    
     func send(for friend: Friend) throws  {
        return DefaultValueRegistry.defaultValue(for: (Void).self)
    }
    
    
}





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/MessageFormatter.swift at 2023-05-21 11:07:49 +0000

//
//  MessageFormatter.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Cuckoo
@testable import SwiftBirthdayGreetings

import Foundation






 class MockMessageFormatter: MessageFormatter, Cuckoo.ProtocolMock {
    
     typealias MocksType = MessageFormatter
    
     typealias Stubbing = __StubbingProxy_MessageFormatter
     typealias Verification = __VerificationProxy_MessageFormatter

     let cuckoo_manager = Cuckoo.MockManager.preconfiguredManager ?? Cuckoo.MockManager(hasParent: false)

    
    private var __defaultImplStub: MessageFormatter?

     func enableDefaultImplementation(_ stub: MessageFormatter) {
        __defaultImplStub = stub
        cuckoo_manager.enableDefaultStubImplementation()
    }
    

    

    

    
    
    
    
     func format(friend: Friend) -> Message {
        
    return cuckoo_manager.call(
    """
    format(friend: Friend) -> Message
    """,
            parameters: (friend),
            escapingParameters: (friend),
            superclassCall:
                
                Cuckoo.MockManager.crashOnProtocolSuperclassCall()
                ,
            defaultCall: __defaultImplStub!.format(friend: friend))
        
    }
    
    

     struct __StubbingProxy_MessageFormatter: Cuckoo.StubbingProxy {
        private let cuckoo_manager: Cuckoo.MockManager
    
         init(manager: Cuckoo.MockManager) {
            self.cuckoo_manager = manager
        }
        
        
        
        
        func format<M1: Cuckoo.Matchable>(friend: M1) -> Cuckoo.ProtocolStubFunction<(Friend), Message> where M1.MatchedType == Friend {
            let matchers: [Cuckoo.ParameterMatcher<(Friend)>] = [wrap(matchable: friend) { $0 }]
            return .init(stub: cuckoo_manager.createStub(for: MockMessageFormatter.self, method:
    """
    format(friend: Friend) -> Message
    """, parameterMatchers: matchers))
        }
        
        
    }

     struct __VerificationProxy_MessageFormatter: Cuckoo.VerificationProxy {
        private let cuckoo_manager: Cuckoo.MockManager
        private let callMatcher: Cuckoo.CallMatcher
        private let sourceLocation: Cuckoo.SourceLocation
    
         init(manager: Cuckoo.MockManager, callMatcher: Cuckoo.CallMatcher, sourceLocation: Cuckoo.SourceLocation) {
            self.cuckoo_manager = manager
            self.callMatcher = callMatcher
            self.sourceLocation = sourceLocation
        }
    
        
    
        
        
        
        @discardableResult
        func format<M1: Cuckoo.Matchable>(friend: M1) -> Cuckoo.__DoNotUse<(Friend), Message> where M1.MatchedType == Friend {
            let matchers: [Cuckoo.ParameterMatcher<(Friend)>] = [wrap(matchable: friend) { $0 }]
            return cuckoo_manager.verify(
    """
    format(friend: Friend) -> Message
    """, callMatcher: callMatcher, parameterMatchers: matchers, sourceLocation: sourceLocation)
        }
        
        
    }
}


 class MessageFormatterStub: MessageFormatter {
    

    

    
    
    
    
     func format(friend: Friend) -> Message  {
        return DefaultValueRegistry.defaultValue(for: (Message).self)
    }
    
    
}





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/MessageSender.swift at 2023-05-21 11:07:49 +0000

//
//  MessageSender.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Cuckoo
@testable import SwiftBirthdayGreetings

import Foundation






 class MockMessageSender: MessageSender, Cuckoo.ProtocolMock {
    
     typealias MocksType = MessageSender
    
     typealias Stubbing = __StubbingProxy_MessageSender
     typealias Verification = __VerificationProxy_MessageSender

     let cuckoo_manager = Cuckoo.MockManager.preconfiguredManager ?? Cuckoo.MockManager(hasParent: false)

    
    private var __defaultImplStub: MessageSender?

     func enableDefaultImplementation(_ stub: MessageSender) {
        __defaultImplStub = stub
        cuckoo_manager.enableDefaultStubImplementation()
    }
    

    

    

    
    
    
    
     func send(message: Message) throws {
        
    return try cuckoo_manager.callThrows(
    """
    send(message: Message) throws
    """,
            parameters: (message),
            escapingParameters: (message),
            superclassCall:
                
                Cuckoo.MockManager.crashOnProtocolSuperclassCall()
                ,
            defaultCall: __defaultImplStub!.send(message: message))
        
    }
    
    

     struct __StubbingProxy_MessageSender: Cuckoo.StubbingProxy {
        private let cuckoo_manager: Cuckoo.MockManager
    
         init(manager: Cuckoo.MockManager) {
            self.cuckoo_manager = manager
        }
        
        
        
        
        func send<M1: Cuckoo.Matchable>(message: M1) -> Cuckoo.ProtocolStubNoReturnThrowingFunction<(Message)> where M1.MatchedType == Message {
            let matchers: [Cuckoo.ParameterMatcher<(Message)>] = [wrap(matchable: message) { $0 }]
            return .init(stub: cuckoo_manager.createStub(for: MockMessageSender.self, method:
    """
    send(message: Message) throws
    """, parameterMatchers: matchers))
        }
        
        
    }

     struct __VerificationProxy_MessageSender: Cuckoo.VerificationProxy {
        private let cuckoo_manager: Cuckoo.MockManager
        private let callMatcher: Cuckoo.CallMatcher
        private let sourceLocation: Cuckoo.SourceLocation
    
         init(manager: Cuckoo.MockManager, callMatcher: Cuckoo.CallMatcher, sourceLocation: Cuckoo.SourceLocation) {
            self.cuckoo_manager = manager
            self.callMatcher = callMatcher
            self.sourceLocation = sourceLocation
        }
    
        
    
        
        
        
        @discardableResult
        func send<M1: Cuckoo.Matchable>(message: M1) -> Cuckoo.__DoNotUse<(Message), Void> where M1.MatchedType == Message {
            let matchers: [Cuckoo.ParameterMatcher<(Message)>] = [wrap(matchable: message) { $0 }]
            return cuckoo_manager.verify(
    """
    send(message: Message) throws
    """, callMatcher: callMatcher, parameterMatchers: matchers, sourceLocation: sourceLocation)
        }
        
        
    }
}


 class MessageSenderStub: MessageSender {
    

    

    
    
    
    
     func send(message: Message) throws  {
        return DefaultValueRegistry.defaultValue(for: (Void).self)
    }
    
    
}





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/SendBirthdateGreetingService.swift at 2023-05-21 11:07:49 +0000

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





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/SendBirthdateGreetingsService.swift at 2023-05-21 11:07:49 +0000

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





// MARK: - Mocks generated from file: SwiftBirthdayGreetings/Domain/TodayDateRetriever.swift at 2023-05-21 11:07:49 +0000

//
//  TodayDateRetriever.swift
//  SwiftBirthdayGreetings
//
//  Created by Fabrizio Duroni on 21/05/23.
//

import Cuckoo
@testable import SwiftBirthdayGreetings

import Foundation






 class MockTodayDateRetriever: TodayDateRetriever, Cuckoo.ProtocolMock {
    
     typealias MocksType = TodayDateRetriever
    
     typealias Stubbing = __StubbingProxy_TodayDateRetriever
     typealias Verification = __VerificationProxy_TodayDateRetriever

     let cuckoo_manager = Cuckoo.MockManager.preconfiguredManager ?? Cuckoo.MockManager(hasParent: false)

    
    private var __defaultImplStub: TodayDateRetriever?

     func enableDefaultImplementation(_ stub: TodayDateRetriever) {
        __defaultImplStub = stub
        cuckoo_manager.enableDefaultStubImplementation()
    }
    

    

    

    
    
    
    
     func get() -> Date {
        
    return cuckoo_manager.call(
    """
    get() -> Date
    """,
            parameters: (),
            escapingParameters: (),
            superclassCall:
                
                Cuckoo.MockManager.crashOnProtocolSuperclassCall()
                ,
            defaultCall: __defaultImplStub!.get())
        
    }
    
    

     struct __StubbingProxy_TodayDateRetriever: Cuckoo.StubbingProxy {
        private let cuckoo_manager: Cuckoo.MockManager
    
         init(manager: Cuckoo.MockManager) {
            self.cuckoo_manager = manager
        }
        
        
        
        
        func get() -> Cuckoo.ProtocolStubFunction<(), Date> {
            let matchers: [Cuckoo.ParameterMatcher<Void>] = []
            return .init(stub: cuckoo_manager.createStub(for: MockTodayDateRetriever.self, method:
    """
    get() -> Date
    """, parameterMatchers: matchers))
        }
        
        
    }

     struct __VerificationProxy_TodayDateRetriever: Cuckoo.VerificationProxy {
        private let cuckoo_manager: Cuckoo.MockManager
        private let callMatcher: Cuckoo.CallMatcher
        private let sourceLocation: Cuckoo.SourceLocation
    
         init(manager: Cuckoo.MockManager, callMatcher: Cuckoo.CallMatcher, sourceLocation: Cuckoo.SourceLocation) {
            self.cuckoo_manager = manager
            self.callMatcher = callMatcher
            self.sourceLocation = sourceLocation
        }
    
        
    
        
        
        
        @discardableResult
        func get() -> Cuckoo.__DoNotUse<(), Date> {
            let matchers: [Cuckoo.ParameterMatcher<Void>] = []
            return cuckoo_manager.verify(
    """
    get() -> Date
    """, callMatcher: callMatcher, parameterMatchers: matchers, sourceLocation: sourceLocation)
        }
        
        
    }
}


 class TodayDateRetrieverStub: TodayDateRetriever {
    

    

    
    
    
    
     func get() -> Date  {
        return DefaultValueRegistry.defaultValue(for: (Date).self)
    }
    
    
}




