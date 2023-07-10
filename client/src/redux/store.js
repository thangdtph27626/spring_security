import authReducer from "./slice/AuthSlice";
import { combineReducers, configureStore } from "@reduxjs/toolkit";

export const store = configureStore({
    reducer: {
      user: authReducer,

    },
    // middleware: (getDefaultMiddleware) =>
    //   getDefaultMiddleware({
    //     serializableCheck: {
    //       ignoredActions: [FLUSH, PAUSE, PERSIST, PURGE, REGISTER ],
    //     },
    //   }),
  });