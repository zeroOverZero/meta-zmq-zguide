# meta-zmq-zguide
This layer contains recipes to build the zguide examples in yocto.

# Compatibility
These have only been tested using `thud`.
The versions of zeromq, cppzmq and zguide are pinned to ensure compatibility.

# Building
To use this layer, add it to your bblayers.conf file.

You can then add the cppzmq-example recipe to the `local.conf` file.
```
echo '# ZeroMQ test application' >> build/conf/local.conf
echo 'IMAGE_INSTALL_append += " cppzmq-example"' >> build/conf/local.conf
```

# Usage
The installed applications will be placed in the `/usr/bin` folder on the target device.

# Acknowledgements
This recipe is just a wrapper to build the examples from the fantastic guide available at:
https://github.com/imatix/zguide.git
