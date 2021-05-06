/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useRef } from 'react';
import type {Node} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
  Alert,
  Button,
  NativeModules,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

var RN = require('react-native')

const { Ivms8700Module } = NativeModules;
import PlayerView from './PlayerView'


const App: () => Node = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  const onClick = () => {
    Ivms8700Module.login();
  }

  const playerRef = useRef(null)

  const onStartLive = () => {
    const sysCode = "544554f2ee1b4dad86a228029ddcce21";
    Ivms8700Module.startLive(sysCode, RN.findNodeHandle(playerRef.current));
  }

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <Button
          title="Login"
          onPress={onClick}
        />
        <PlayerView
          style={{height:200}}
          ref={playerRef}
        />
        <Button
          title="Start Live"
          onPress={onStartLive}
        />
        <Text>THE END.</Text>
        <Button
          title="Stop"
          onPress={()=>Ivms8700Module.stopCommand()}
        />
        <Button
          title="Top"
          onPress={()=>Ivms8700Module.startCommand(21)}
        />
        <Button
          title="BOttom"
          onPress={()=>Ivms8700Module.startCommand(22)}
        />
        <Button
          title="Left"
          onPress={()=>Ivms8700Module.startCommand(23)}
        />
        <Button
          title="Right"
          onPress={()=>Ivms8700Module.startCommand(24)}
        />
        <Button
          title="Zoom In"
          onPress={()=>Ivms8700Module.startCommand(11)}
        />
        <Button
          title="Zoom Out"
          onPress={()=>Ivms8700Module.startCommand(12)}
        />
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
