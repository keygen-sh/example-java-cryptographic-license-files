# Example Java Cryptographic License Files

This is an example of how to verify and decrypt [cryptographic license files][docs] in Java 18 using Bouncy Castle.

This example verifies the `aes-256-gcm+ed25519` algorithm.

## Running the example

First, install dependencies with [`mvn`][maven]:

```
mvn compile
```

Then run the program:

```
mvn exec:java -Dexec.mainClass=sh.keygen.example.Main
```

You should see log output indicating the current license file is valid:

```
License file signature is valid!
License file was successfully decrypted!
> Decrypted: {"data":{"id":"a4797354-b53a-4641-b14d-89c4e87f9412","type":"licenses","attributes":{"name":"Java Example","key":"33362C-D254BA-F54C3C-DAAE48-C71975-V3","expiry":null,"status":"ACTIVE","uses":0,"suspended":false,"scheme":null,"encrypted":false,"strict":false,"floating":false,"concurrent":false,"protected":true,"maxMachines":1,"maxCores":null,"maxUses":null,"requireHeartbeat":false,"requireCheckIn":false,"lastValidated":null,"lastCheckIn":null,"nextCheckIn":null,"metadata":{},"created":"2022-04-01T13:17:43.813Z","updated":"2022-04-01T13:17:43.813Z"},"relationships":{"account":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52"},"data":{"type":"accounts","id":"1fddcec8-8dd3-4d8d-9b16-215cac0f9b52"}},"product":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/product"},"data":{"type":"products","id":"6db9ac6e-ea9e-4943-8462-a0315dda0f2e"}},"policy":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/policy"},"data":{"type":"policies","id":"70bda6e4-2b9e-4100-946a-103164a2abc6"}},"group":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/group"},"data":null},"user":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/user"},"data":null},"machines":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/machines"},"meta":{"cores":0,"count":0}},"tokens":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/tokens"}},"entitlements":{"links":{"related":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412/entitlements"}}},"links":{"self":"/v1/accounts/1fddcec8-8dd3-4d8d-9b16-215cac0f9b52/licenses/a4797354-b53a-4641-b14d-89c4e87f9412"}},"meta":{"issued":"2022-04-01T13:18:09.930Z","expiry":"2022-05-01T13:18:09.930Z","ttl":2629746}}
```

## Questions?

Reach out at [support@keygen.sh][] if you have any
questions or concerns!

[docs]: https://keygen.sh/docs/api/cryptography/#cryptographic-lic
[maven]: https://mvnrepository.com/
[support]: mailto:support@keygen.sh
