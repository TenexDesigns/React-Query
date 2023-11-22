To use `ReactQueryDevtools` in a Next.js application, you need to wrap your application with `QueryClientProvider` and include `ReactQueryDevtools` inside this provider.

First, install `react-query` and `react-query-devtools`:

```bash
npm install react-query @tanstack/react-query-devtools
```

Next, in your `_app.js` file (or `_app.tsx` if you're using TypeScript), add the `QueryClientProvider` and `ReactQueryDevtools`:

```jsx
import { QueryClient, QueryClientProvider } from "react-query";
import { ReactQueryDevtools } from "react-query/devtools";

// Create a client
const queryClient = new QueryClient();

function MyApp({ Component, pageProps }) {
  return (
    <QueryClientProvider client={queryClient}>
      <Component {...pageProps} />
      <ReactQueryDevtools initialIsOpen={false} />
    </QueryClientProvider>
  );
}

export default MyApp;
```

In this code, `QueryClientProvider` is wrapping the entire application, and `ReactQueryDevtools` is included as a child component. The `initialIsOpen` prop determines whether the devtools panel should be open by default when the application loads. Setting it to `false` means the panel will be closed by default.

The devtools panel will only appear in development mode (`process.env.NODE_ENV === 'development'`), so you don't need to worry about it appearing in a production build.

If youre using a modern bundler that supports package exports (like webpack 5 or Parcel 2), you can import `ReactQueryDevtools` from a different path to get a production version of the devtools. This can be useful if you want to include the devtools in a production build for debugging purposes. You'd import it like this:

```jsx
const ReactQueryDevtools = React.lazy(() =>
  import("@tanstack/react-query-devtools/production").then((module) => ({
    default: module.ReactQueryDevtools,
  }))
);
```

Remember that using `React.lazy` for code-splitting like this requires a fallback to be provided, usually through `React.Suspense`.

Note: The Devtools are a great aid when developing with React Query as they provide a visualization of the state and status of your queries and mutations, which can be very helpful for debugging [Source 9](https://github.com/TanStack/query/blob/main/docs/react/devtools.md).





....
