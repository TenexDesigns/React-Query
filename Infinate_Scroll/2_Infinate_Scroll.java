
import React, { useEffect, useState } from 'react';
import { useInfiniteQuery } from 'react-query';
import axios from 'axios';

const pageSize = 10;

const InfinitePosts = () => {
 const [isLoadingMore, setIsLoadingMore] = useState(false);

 const fetchPosts = async ({ pageParam }) => {
   const response = await axios.get(`https://jsonplaceholder.typicode.com/posts?_page=${pageParam}&_limit=${pageSize}`);
   return response.data;
 };

 const { data, error, fetchNextPage, hasMore, isLoading, isFetchingNextPage } = useInfiniteQuery('posts', fetchPosts, {
   getNextPageParam: (lastPage) => {
     if (lastPage.length === 0) {
       return undefined; // No more pages to fetch
     }
     return lastPage[lastPage.length - 1].id + 1; // Next page ID
   },
 });

 const handleScroll = (event) => {
   const { scrollTop, scrollHeight, clientHeight } = event.target;
   if (scrollTop + clientHeight >= scrollHeight - 50 && !isLoadingMore && !isFetchingNextPage) {
     setIsLoadingMore(true);
     fetchNextPage().then(() => setIsLoadingMore(false));
   }
 };

 useEffect(() => {
   window.addEventListener('scroll', handleScroll);

   // Clean up the event listener when the component unmounts
   return () => {
     window.removeEventListener('scroll', handleScroll);
   };
 }, []);

 if (isLoading) {
   return <div>Loading...</div>;
 }

 if (error) {
   return <div>An error occurred: {error}</div>;
 }

 return (
   <div>
     {data.pages.map((page, pageIndex) => (
       <div key={pageIndex}>
         {page.map((item) => (
           <div key={item.id}>{item.title}</div>
         ))}
       </div>
     ))}

     {hasMore && (
       <button onClick={() => fetchNextPage()} disabled={isFetching}>
         {isFetching ? 'Loading more...' : 'Load More'}
       </button>
     )}
   </div>
 );
};

export default InfinitePosts;








---------------------------------------------------------------------------------------------------------------------------------------------------
Infinite queries in React Query refer to the practice of continuously fetching additional data as the user scrolls through a list or performs some action that triggers the loading of more data. This is often used in scenarios where you have a potentially endless list of items.

### Infinite Queries Example:

Let's consider an example where you have a list of items, and you want to fetch additional items as the user scrolls down.

```javascript
// InfiniteList.js

import React from 'react';
import { useInfiniteQuery } from 'react-query';
import axios from 'axios';

const fetchInfiniteData = async ({ pageParam = 1 }) => {
  const response = await axios.get(`https://api.example.com/items?page=${pageParam}`);
  return response.data;
};

const InfiniteList = () => {
  const {
    data,
    fetchNextPage,
    hasMore,
    isFetching,
    isLoading,
    isError,
  } = useInfiniteQuery('infiniteData', fetchInfiniteData, {
    // Specify how to get the next page
    getNextPageParam: (lastPage) => lastPage.nextPage,
  });

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return <div>Error: {isError.message}</div>;
  }

  return (
    <div>
      {data.pages.map((page, pageIndex) => (
        <div key={pageIndex}>
          {page.data.map((item) => (
            <div key={item.id}>{item.name}</div>
          ))}
        </div>
      ))}

      {hasMore && (
        <button onClick={() => fetchNextPage()} disabled={isFetching}>
          {isFetching ? 'Loading more...' : 'Load More'}
        </button>
      )}
    </div>
  );
};

export default InfiniteList;
```

In this example:

- The `useInfiniteQuery` hook is used to fetch infinite data.
- The `fetchInfiniteData` function takes a `pageParam` as a parameter, allowing you to fetch data for a specific page.
- The `getNextPageParam` option is used to determine how to get the next page's parameter.
- The `hasMore` boolean indicates whether there is more data to fetch.

### Key Concepts:

1. **`getNextPageParam`:**
   - The `getNextPageParam` option is used to determine how to get the next page's parameter. This function takes the last page's data as a parameter and should return the parameter needed to fetch the next page.

2. **`fetchNextPage`:**
   - The `fetchNextPage` function is provided by React Query and can be called to fetch the next page of data.

3. **`hasMore`:**
   - The `hasMore` boolean indicates whether there is more data to fetch.

4. **`isFetching`:**
   - The `isFetching` boolean indicates whether the query is currently fetching data.

### Where to Use Infinite Queries:

1. **Endless Lists:**
   - Use infinite queries when dealing with lists that could potentially be endless, and you want to load more items as the user scrolls.

2. **Optimizing Performance:**
   - Fetching data in an infinite manner helps optimize the performance of your application, especially when dealing with large datasets.

3. **Infinite Scrolling:**
   - Infinite queries are commonly used in conjunction with infinite scrolling patterns.

By implementing infinite queries, you can create a seamless and performant experience for users navigating through large lists of items in your React applications.













  ...
