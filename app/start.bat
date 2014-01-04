@echo off
netsh wlan set hostednetwork mode=allow ssid=Mickey key=asdf1234
netsh wlan start hostednetwork
