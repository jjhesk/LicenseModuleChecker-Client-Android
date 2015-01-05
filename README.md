LicenseModuleChecker-Client-Android
===================================

Android client side SDK for software verifications

This is the client side SDK for license checker. 

Instruction:
Download the SDK .jar from this link.
include that sdk in the source code.

##Setup instruction
1. download the package or include the library folder
2. within your project implement the sample code
3. 

Example 1
```java
 if (!checking) {
                checking = true;
                Tool.trace(_act, "checking license now!");
                plugable = new HKMCheckerPlugable("__YOUR_PRODUCT_KEY__", getActivity());
                plugable.netStartCheck(this);
                checkingView.setText("Checking..");
}
```
