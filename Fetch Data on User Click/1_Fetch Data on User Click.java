The enabled prop is set to false initially, preventing the query from fetching data automatically upon component mounting.
  The button click handler calls  setEnable method and set the Enable prop to true  to manually initiate the data fetch. 

Do not call data.map() when data is not undefined. You can do this by adding a condition to check if data is not undefined before calling map(). Here's how you can do it:{data &&data.map}


      
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
       
        if (isLoading) return <div>'Loading...'<div>;
        if (error) return <div>`An error occurred: ${error.message}`</div>;
       
        return (
          <div>
            <div> Data</div>
            { data &&
        data.map(post => (
              <p key={post.id}>{post.title}</p>
            ))  }
            <button onClick={() => setEnabled(true)}>Fetch Data</button>
          </div>
        );
       };
       
