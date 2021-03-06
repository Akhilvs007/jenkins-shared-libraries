package Utils.Exceptions
import groovy.transform.InheritConstructors

@InheritConstructors
class DockerRegistryIsNotDefinedException extends Exception{}

@InheritConstructors
class InvalidBitbucketStatusException extends Exception{}

@InheritConstructors
class HelmUpgradeException extends Exception {}

@InheritConstructors
class HelmLintException extends Exception {}

@InheritConstructors
class StageResultException extends Exception {}
