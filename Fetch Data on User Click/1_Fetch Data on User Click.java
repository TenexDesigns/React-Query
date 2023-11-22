The enabled prop is set to false initially, preventing the query from fetching data automatically upon component mounting.
  The button click handler calls the refetch() method to manually initiate the data fetch. and set the Enable prop to true to fetch the data


      
import { useQuery } from 'react-query';
import axios from 'axios';
import { useState } from 'react';
       
const fetchData = async () => {
const response = await axios.get('https://jsonplaceholder.typicode.com/posts');
    return response.data;
};
       
       export const MyComponent = () => {
        const [enabled, setEnabled] = useState(false);
        const { isLoading, error, data } = useQuery('posts', fetchData, {
          enabled: enabled,
        });
       
        if (isLoading) return 'Loading...';
        if (error) return `An error occurred: ${error.message}`;
       
        return (
          <div>
            <div> Data</div>
            { enabled &&
        data.map(post => (
              <p key={post.id}>{post.title}</p>
            ))  }
            <button onClick={() => setEnabled(true)}>Fetch Data</button>
          </div>
        );
       };
       
