@UserE2E
Feature: User E2E Controller Testleri


  @UserE2E_TC01
  Scenario: TC 01 - Successful user registration
    Given  User navigates to the GetLandEstate website
    When User clicks on the register button
    And enters data into the First Name field
    And enters data into the Last Name field
    And enters data into the Phone Number field
    And enters data into the Email field
    And enters data into the Password field
    And enters data into the Confirm Password field
    And clicks  the 'I understand and agree to GetLandEstate's Terms of Use and Privacy Policy' checkbox
    But user waits 2 second
    And clicks on the Register Account button
    Then verify registration is completed successfully
    But user waits 2 second
    And user close driver


  Scenario: TC03 Kayitli Kullanici DB üzerinden test edilir
    Given Database baglantisi kurulur
    Then Kayitli User datasi dogrulanir



  @ApiAdmin
  Scenario: TC02 | Kayitli kullanici API üzerinden test edilir
    Given Get User icin Admin yetkisiyle giris yapilir
    Given Get User icin URL duzenlenir
    And Get User icin expected data duzenlenir
    When Get User icin GET request gonderilir ve response alinir
    Then Get User icin Status kodunun 200 oldugu dogrulanir
    And Get User icin Response body dogrulanir


#  @Api_TC03
#  Scenario: TC03| Kullanici hesabini silebilmelidir
#    Given User Hesap silmek icin URL duzenlenir
#    When User Hesap silmek icin DELETE request gonderilir ve response alinir
#    Then User Hesap silmek icin Status kodunun 200 oldugu dogrulanir
#







