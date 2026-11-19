## UI automation checklist

- [ ] Page-object locators updated if storefront markup changed
- [ ] No fixed sleeps introduced
- [ ] Assertions remain in TestNG test classes
- [ ] Tests do not submit real payment or create real orders
- [ ] Local `mvn clean test -Pci` passes
